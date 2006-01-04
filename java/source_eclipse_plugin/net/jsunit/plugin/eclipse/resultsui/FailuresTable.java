package net.jsunit.plugin.eclipse.resultsui;

import net.jsunit.plugin.eclipse.JsUnitPlugin;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableItem;

public class FailuresTable extends Table {

	private ViewContentProvider contentProvider;

	public FailuresTable(Composite parent, int style, ViewContentProvider provider) {
		super(parent, style);
		this.contentProvider = provider;
	}

	public void refresh() {
		JsUnitPlugin.getDisplay().syncExec(
			new Runnable() {
				public void run() {
					removeAll();
					repopulateFromContentProvider();
				}
			}
		);
	}
	
	private void repopulateFromContentProvider() {
		for (TestCaseResultNode node : contentProvider.getProblemTestCaseResultNodes()) {
			TableItem tableItem = new TestCaseResultTableItem(this, node);
			showItem(tableItem);
		}
	}
	
	protected void checkSubclass () {
	}
}