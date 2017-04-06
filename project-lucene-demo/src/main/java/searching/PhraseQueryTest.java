package searching;

import common.TestUtil;
import junit.framework.TestCase;
import org.apache.lucene.analysis.Analyzer;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.analysis.WhitespaceAnalyzer;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.RAMDirectory;
import org.apache.lucene.util.Version;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/22
 * @description
 */
public class PhraseQueryTest extends TestCase {
    private Directory dir;
    private IndexSearcher searcher;
    private Analyzer analyzer;

    protected void setUp() throws IOException {
        analyzer = new WhitespaceAnalyzer();
        dir = TestUtil.getBookIndexDirectory();
        searcher = new IndexSearcher(dir);
    }

    protected void tearDown() throws IOException {
        searcher.close();
        dir.close();
    }

    private boolean matched(String[] phrase, int slop) throws IOException {
        PhraseQuery query = new PhraseQuery();
        query.setSlop(slop);
        for(String word : phrase){
            query.add(new Term("field", word));
        }
        TopDocs matches = searcher.search(query, 10);
        return matches.totalHits > 0;
    }

    public void testSlopComparison() throws IOException {
        String[] phrase = new String[]{"quick", "fox"};

        assertFalse("exact phrase not found", matched(phrase, 0));

        assertTrue("close enough", matched(phrase, 1));
    }

    public void testReverse() throws IOException {
        String[] pharse = new String[]{"fox", "quick"};

        assertFalse("hot flop", matched(pharse,2));

        assertTrue("hop hop slop", matched(pharse,3));
    }

    public void testMultiple() throws IOException {
        assertFalse("not close enough", matched(new String[] {"quick", "jumped", "lazy"}, 3));

        assertTrue("just enough", matched(new String[] {"quick", "jumped", "lazy"}, 4));

        assertFalse("almost but not quite", matched(new String[] {"lazy", "jumped", "quick"}, 7));

        assertTrue("bingo", matched(new String[] {"lazy", "jumped", "quick"}, 8));
    }

    private void indexSingleFieldDocs(Field[] fields,Directory directory) throws IOException {
        IndexWriter writer = new IndexWriter(directory, new WhitespaceAnalyzer(),
                IndexWriter.MaxFieldLength.UNLIMITED);
        for(Field f : fields){
            Document doc = new Document();
            doc.add(f);
            writer.addDocument(doc);
        }
        writer.optimize();
        writer.close();
    }

    public void testWildcard() throws IOException {
        Directory directory = new RAMDirectory();
        indexSingleFieldDocs(new Field[]
                {
                        new Field("contents", "wild", Field.Store.YES, Field.Index.ANALYZED ),
                        new Field("contents", "child", Field.Store.YES, Field.Index.ANALYZED),
                        new Field("contents", "mild", Field.Store.YES, Field.Index.ANALYZED),
                        new Field("contents", "mildew", Field.Store.YES, Field.Index.ANALYZED)
                }, directory);

        IndexSearcher searcher = new IndexSearcher(directory);
        Query query = new WildcardQuery(new Term("contents", "?ild*"));
        TopDocs matches = searcher.search(query, 10);
        assertEquals("child no match", 3, matches.totalHits);
        assertEquals("score the same", matches.scoreDocs[0].score, matches.scoreDocs[1].score, 0.0);
        assertEquals("score the same", matches.scoreDocs[1].score, matches.scoreDocs[2].score, 0.0);
        searcher.close();
    }

    public void testFuzzy() throws IOException {
        Directory directory = new RAMDirectory();
        indexSingleFieldDocs( new Field[]{
                        new Field("contents", "fuzzy", Field.Store.YES, Field.Index.ANALYZED ),
                        new Field("contents", "wuzzy", Field.Store.YES, Field.Index.ANALYZED)
        }, directory);
        IndexSearcher searcher = new IndexSearcher(directory);
        Query query = new FuzzyQuery(new Term("contents", "wuzza"));
        TopDocs matches = searcher.search(query, 10);
        assertEquals("both close enought", 2, matches.totalHits);
        assertTrue("wuzzy close than fuzzy", matches.scoreDocs[0].score > matches.scoreDocs[1].score);
        searcher.close();

    }

    public void testToString(){
        BooleanQuery query = new BooleanQuery();
        query.add(new FuzzyQuery(new Term("field", "kountry")),BooleanClause.Occur.MUST);
        query.add(new TermQuery(new Term("title", "western")), BooleanClause.Occur.SHOULD);
        assertEquals("both kinds", "+kountry~0.5 title:western", query.toString("field"));
    }

    public void testTermQuery() throws ParseException {
        QueryParser parser = new QueryParser(Version.LUCENE_30,"subject", new SimpleAnalyzer());
        Query query = parser.parse("computers");
        System.out.println("term: " + query);
    }

    public void testTermRangeQuery() throws ParseException, IOException {
        Query query = new QueryParser(Version.LUCENE_30, "subject", new SimpleAnalyzer())
                .parse("title2:[Q TO V]");
        assertTrue(query instanceof TermRangeQuery);
        TopDocs matches = searcher.search(query, 10);
        assertTrue(TestUtil.hitsIncludeTitle(searcher, matches, "Tapestry in Action"));
        query = new QueryParser(Version.LUCENE_30,"subject", analyzer)
                .parse("title2:{Q TO \"Tapestry in Action\" }");
        matches = searcher.search(query, 10);

        assertFalse(TestUtil.hitsIncludeTitle(searcher, matches, "Tapestry in Action"));
    }

    public void testLowercasing() throws ParseException {
        Query q = new QueryParser(Version.LUCENE_30, "field", analyzer).parse("PrefixQuery*");

        assertEquals("lowercased", "prefixquery*", q.toString("field"));

        QueryParser qp = new QueryParser(Version.LUCENE_30, "field", analyzer);
        qp.setLowercaseExpandedTerms(false);
        q = qp.parse("PrefixQuery");
        assertEquals("not lowercased", "PrefixQuery", q.toString("field"));

    }

    public void testPhraseQuery() throws ParseException {
        Query q = new QueryParser(Version.LUCENE_30, "field", new StandardAnalyzer(Version.LUCENE_30))
                .parse("\"this is Some Phrase*\"");
        assertEquals("analyzed", "\"? ? some phrase\"", q.toString("field"));
    }

    public void testlop() throws ParseException {
        Query q = new QueryParser(Version.LUCENE_30, "field", analyzer).parse("\"exact phrase\"");
        assertEquals("zero slop", "\"exact phrase\"", q.toString("field"));
        QueryParser qp = new QueryParser(Version.LUCENE_30, "field", analyzer);
        qp.setPhraseSlop(5);
        q = qp.parse("\"sloppy phrase\"");
        assertEquals("sloppy, implicitly", "\"sloppy phrase\"~5", q.toString("field"));
    }

    public void testFuzzyQuery() throws ParseException {
        QueryParser parser = new QueryParser(Version.LUCENE_30, "subject", analyzer);
        Query query = parser.parse("kountry~");
        System.out.println("fuzzy: " + query);
        query = parser.parse("kountry~0.7");
        System.out.println("fuzzy 2: "+ query);
    }

    public void testGrouping() throws ParseException, IOException {
        Query query = new QueryParser(Version.LUCENE_30, "subject", analyzer)
                .parse("(agile OR extreme) AND methodology");
        TopDocs matches = searcher.search(query, 10);

        assertTrue(TestUtil.hitsIncludeTitle(searcher, matches, "Extreme Programming Explained"));

        assertTrue(TestUtil.hitsIncludeTitle(searcher, matches, "The Pragmatic Programmer"));
    }



}
