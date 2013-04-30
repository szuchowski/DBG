import java.awt.BorderLayout;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;


public class DeleteDialog extends SQLDialog{

	private QueryPanel main;
	
	public DeleteDialog() {
		super();
		
		JPanel topPanel = new JPanel();		
		topPanel.add(new JLabel("Delete entries from"));
		topPanel.add(tables);
		topPanel.add(new JLabel("where"));
		topPanel.setBorder(BorderFactory.createEtchedBorder());
		
		updateQueryPanels();
		
		this.add(topPanel, BorderLayout.NORTH);
		this.finalize();
	}
	
	public DeleteDialog(ArrayList<String> n, ArrayList<String> nn, String tn) {
		super(n, nn, tn);
		
		JPanel topPanel = new JPanel();		
		topPanel.add(new JLabel("Delete entries from " + curTable + " where"));
		topPanel.setBorder(BorderFactory.createEtchedBorder());
		
		updateQueryPanels();
		
		this.add(topPanel, BorderLayout.NORTH);
		this.finalize();
	}
	
	public void close(){
		// get the values from any contained QueryPanels
		// and do something with them
		
		super.close();
	}

	public void updateQueryPanels() {
		super.updateQueryPanels();
		
		if(this.isAncestorOf(main)){
			this.remove(main);
		}
		main = new QueryPanel(attribNames, attribTypes, curTable);
		this.add(main, BorderLayout.CENTER);
		this.finalize();
	}
	
	public Query getQuery(){
		Query q = new Query(Query.DELETE, curTable);
		ArrayList<String> ops = main.getOperators();
		ArrayList<String> values = main.getValues();
		
		for(int i = 0; i < attribNames.size(); i++){
			if(values.get(i).length() > 0){
				q.addCondition(attribTypes.get(i).intValue(), attribNames.get(i), ops.get(i), values.get(i));
			}
		}
		
		return q;
	}

}
