/**
 * Converts 3x3 coordinates to 1x9 coordinates and vice versa
 *
 * @author Colin Elkin
 */

public class CoordConversion {

	/**
	 * Converts 1x9 coordinates  to 3x3 coordinates
	 * 
	 * @param outer number
	 *		PRECONDITION: 0 <= m <= 2
	 *
	 * @param inner number
	 *		PRECONDITION: 0 <= n <= 2
	 *            
	 * @return single integer from outer and inner
	 *		POSTCONDITION: 0 <= output <= 8
	 */

	public int oneCoord(int outer, int inner) {
		return 3 * outer + inner;
	} // end oneCoord

	/**
	 * Converts 1x9 coordinates to 3x3 coordinates
	 * 
	 * @param m number to be converted from 1x9 to 3x3
	 *		PRECONDITION: 0 <= m <= 8
	 *            
	 * @return array of integers indicating outer and inner, respectively
	 *		POSTCONDITION: 0 <= each index of output array <= 2
	 *		POSTCONDITION: output array must be size 2
	 */

	public int[] twoCoords(int m) {
		//System.out.println(m);
		int i = 0, j = 0;
		if (m > 5) {
			i = 2;
			j = m - 6;
		}
		if (m > 2 && m < 6) {
			i = 1;
			j = m - 3;
		}
		if (m < 3) {
			i = 0;
			j = m;
		}
		//System.out.println(i + " " + j);
		return new int[] {i, j};
	} // end twoCoords

} // end CoordConversion
