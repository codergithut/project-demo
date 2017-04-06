package searching;

import common.TestUtil;
import junit.framework.TestCase;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.NumericRangeQuery;
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.Directory;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/22
 * @description
 */
public class NumericRangeQueryTest extends TestCase{
    public void testInclusive() throws IOException {
        Directory dir = TestUtil.getBookIndexDirectory();
        IndexSearcher searcher = new IndexSearcher(dir);
        NumericRangeQuery query = NumericRangeQuery.newIntRange("pubmonth", 200605, 200609, true, true);
        TopDocs matches = searcher.search(query, 10);
        assertEquals(1,matches.totalHits);
        searcher.close();
        dir.close();
    }
}
