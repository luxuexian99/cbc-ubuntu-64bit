import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
    TestCursor.class,
    TestTextUtils.class,
    TestAsmUtils.class,
    TestListUtils.class
})
public class TestAll {
    static public void main(String[] args) {
        JUnitCore.main(TestAll.class.getName());
    }
}
