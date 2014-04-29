/**
 * 
 */
package pl.doa.wicket.angularjs.test;

import org.apache.wicket.markup.html.WebPage;

/**
 * @author activey
 * 
 */
public class TestPage extends WebPage {

	private static final long serialVersionUID = 1L;

    public TestPage() {
        add(new TestApplicationPanel());
    }
}
