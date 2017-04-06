package searching;

import common.TestUtil;
import junit.framework.TestCase;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/22
 * @description
 */
public class TermQueryTest extends TestCase {

    public void testKeyword() throws IOException {
        Directory dir = TestUtil.getBookIndexDirectory();
        IndexSearcher searcher = new IndexSearcher(dir);

        Term t = new Term("isbn", "9781935182023");
        Query query = new TermQuery(t);
        TopDocs docs =searcher.search(query, 10);
        assertEquals("JUnit in Action, Second Edition",1,docs.totalHits);
        dir.close();

    }




}
