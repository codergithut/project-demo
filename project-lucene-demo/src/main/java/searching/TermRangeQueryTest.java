package searching;

import common.TestUtil;
import junit.framework.TestCase;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/22
 * @description
 */
public class TermRangeQueryTest extends TestCase{
    public void testTermRangeQuery() throws IOException {
        Directory dir = TestUtil.getBookIndexDirectory();
        IndexSearcher searcher = new IndexSearcher(dir);
        TermRangeQuery query = new TermRangeQuery("title2", "d", "j", true, true);
        TopDocs matches = searcher.search(query,100);
        assertEquals(3, matches.totalHits);
        searcher.close();
        dir.close();
    }
}
