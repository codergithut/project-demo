package searching;

import common.TestUtil;
import junit.framework.TestCase;
import org.apache.lucene.index.Term;
import org.apache.lucene.search.*;
import org.apache.lucene.store.Directory;

import java.io.IOException;

/**
 * @author <a href="mailto:Administrator@gtmap.cn">Administrator</a>
 * @version 1.0, 2017/3/22
 * @description
 */
public class BooleanQueryTest extends TestCase {
    public void testAnd() throws IOException {
        TermQuery searchingBooks = new TermQuery(new Term("subject", "search"));
        Query books2010 = NumericRangeQuery.newIntRange("pubmonth", 201001, 201012, true, true);
        BooleanQuery searchingBooks2010 = new BooleanQuery();
        searchingBooks2010.add(searchingBooks, BooleanClause.Occur.MUST);
        searchingBooks2010.add(books2010, BooleanClause.Occur.MUST);

        Directory dir = TestUtil.getBookIndexDirectory();
        IndexSearcher searcher = new IndexSearcher(dir);
        TopDocs matches = searcher.search(searchingBooks2010, 10);

        assertTrue(TestUtil.hitsIncludeTitle(searcher, matches, "Lucene in Action, Second Edition"));
        searcher.close();
        dir.close();
    }

}
