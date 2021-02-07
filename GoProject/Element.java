/**
 * 
 */
package go;


/**
 * @author afatc
 *
 */
public abstract class Element {
	private Intersection position;

	public Element(Intersection position) {
		this.position = position;
	}

	public Intersection getPosition() {
		return position;
	}

	public void setPosition(Intersection position) {
		this.position = position;
	}
}
