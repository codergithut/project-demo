package searching;

import junit.framework.TestCase;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.Term;
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
public class NearRealTimeTest extends TestCase {
    public void testNearRealTime() throws IOException {
        Directory dir = new RAMDirectory();
        IndexWriter writer = new IndexWriter(dir, new StandardAnalyzer(Version.LUCENE_30),
                IndexWriter.MaxFieldLength.UNLIMITED);
        for(int i=0; i < 10; i++){
            Document doc = new Document();
            doc.add(new Field("id", ""+i, Field.Store.NO, Field.Index.NOT_ANALYZED_NO_NORMS));
            doc.add(new Field("text", "aaa", Field.Store.NO, Field.Index.ANALYZED));
            writer.addDocument(doc);
        }
        IndexReader reader = writer.getReader();
        IndexSearcher searcher = new IndexSearcher(reader);

        Query query = new TermQuery(new Term("text","aaa"));
        TopDocs docs = searcher.search(query,1);
        assertEquals(10,docs.totalHits);

        writer.deleteDocuments(new Term("id","7"));

        Document doc = new Document();
        doc.add(new Field("id", "11", Field.Store.NO, Field.Index.NOT_ANALYZED_NO_NORMS));
        doc.add(new Field("text", "bbb", Field.Store.NO, Field.Index.ANALYZED));
        writer.addDocument(doc);

        IndexReader newReader = reader.reopen();
        assertFalse(reader == newReader);
        reader.close();
        searcher = new IndexSearcher(newReader);

        TopDocs hits = searcher.search(query,10);

        ScoreDoc[] sd = hits.scoreDocs;
        for (int i =0; i < sd.length; i++) {
            System.out.println(newReader.document(sd[i].doc));
            System.out.println("Explanation:"+ (searcher.explain(query, sd[i].doc)));
        }

        assertEquals(9, hits.totalHits);
        query = new TermQuery(new Term("text", "bbb"));
        hits = searcher.search(query,1);
        assertEquals(1, hits.totalHits);

        newReader.close();
        writer.close();
    }
}
