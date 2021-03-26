package mainP;
import presentationP.ReadInput;
/**
 * From this class starts the entire project
 * @author mirce
 *
 */
public class Project {
	public static void main(String[] args) {
		ReadInput inp = new ReadInput(args[1]);
		inp.getInfo();
	}
}
