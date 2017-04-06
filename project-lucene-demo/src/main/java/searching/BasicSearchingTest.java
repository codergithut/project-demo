package searching;

import common.Config;
import common.TestUtil;
import junit.framework.TestCase;
import org.apache.lucene.analysis.SimpleAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryParser.ParseException;
import org.apache.lucene.queryParser.QueryParser;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;
import org.apache.lucene.util.Version;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/20
 * @description
 */
public class BasicSearchingTest extends TestCase {
    public void testTerm() throws IOException {

        Directory dir = TestUtil.getBookIndexDirectory();
        IndexSearcher searcher = new IndexSearcher(dir);

        Term t = new Term("subject","ant");
        Query query = new TermQuery(t);
        TopDocs docs = searcher.search(query,10);

        assertEquals("Ant in Action",1,docs.totalHits);
        t = new Term("subject","junit");
        docs = searcher.search(new TermQuery(t),10);
        assertEquals("JUnit in Action, Second Editor",2,docs.totalHits);

        searcher.close();
        dir.close();
    }

    public void testQueryParser() throws IOException, ParseException {
        Directory dir = TestUtil.getBookIndexDirectory();
        IndexSearcher searcher = new IndexSearcher(dir);

        QueryParser parser = new QueryParser(Version.LUCENE_30,"contents",new SimpleAnalyzer());

        Query query = parser.parse("+JUNIT +ANT -MOCK");
        TopDocs docs = searcher.search(query,10);
        Document d = searcher.doc(docs.scoreDocs[0].doc);
        assertEquals("Ant in Action",d.get("title"));

        query =parser.parse("mock OR junit");
        docs =searcher.search(query,10);
        assertEquals("ANT in Actin," + "JUnit in Action, Second Edition",2,docs.totalHits);
    }


}
