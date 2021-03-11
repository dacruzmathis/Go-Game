/**
 * 
 */
package go;

import java.util.ArrayList;
import java.util.List;

/**
 * @author afatc
 *
 */
public class Liberty {
	private List<Intersection> liberty;
	
	public Liberty() {
		this.liberty = new ArrayList<Intersection>();
	}
	
	public void put(Intersection position) {
		liberty.add(position);
	}

	public List<Intersection> getLiberty() {
		return liberty;
	}

	public void setLiberty(List<Intersection> liberty) {
		this.liberty = liberty;
	}
}
