
/**
 * @author Alexia BOGAERT & Sacha KOZMA
 */
public final class Seam {

    /**
     * Compute shortest path between {@code from} and {@code to}
     * @param successors adjacency list for all vertices
     * @param costs weight for all vertices
     * @param from first vertex
     * @param to last vertex
     * @return a sequence of vertices, or {@code null} if no path exists
     */
    public static int[] path(int[][] successors, float[] costs, int from, int to) {
        // TODO path
        return null;
    }

    /**
     * Find best seam
     * @param energy weight for all pixels
     * @return a sequence of x-coordinates (the y-coordinate is the index)
     */
    public static int[] find(float[][] energy) {
    	int longueurTableau = energy.length * energy[0].length;

    	
        int[][] successors = new int [longueurTableau + 2][];
        float[] costs = new float [longueurTableau + 2];
        
        // Création du pixel "d'entrée"
        
        successors[longueurTableau] = new int [energy[0].length]; 
        for (int i = 0; i < energy[0].length; i++) {
			successors[longueurTableau][i] = i;
		}
        costs[longueurTableau] = 0;
        
        //Création du pixel "de fin"
        
        successors[longueurTableau + 1] = new int[] {};
        costs[longueurTableau + 1] = 0;
        
        for (int i = 0; i < energy[0].length; i++) {
			successors[longueurTableau - 1 - i] = new int[] {longueurTableau + 1};
			
			
		}
        
        // Création des successeurs des pixels restants
        
        for (int i = 0; i < (energy.length - 1) ; i++) {
			for (int j = 0; j < energy[0].length; j++) {
				
				if (j == 0) {
					successors[(i * energy[0].length) + j] = new int[] {((i+1) * energy[0].length) + j, ((i+1) * energy[0].length) + j + 1};
				} else if (j == energy[0].length - 1) {
					successors[(i * energy[0].length) + j] = new int[] {((i+1) * energy[0].length) + (j - 1), ((i+1) * energy[0].length) + j};
				} else { 
					successors[(i * energy[0].length) + j] = new int[] {((i+1) * energy[0].length) + (j - 1), ((i+1) * energy[0].length) + j,((i+1) * energy[0].length) + j + 1};
				}
				
			}
		}
        
        // Création du tableau costs
        
        for (int i = 0; i < energy.length; i++) {
			for (int j = 0; j < energy[0].length; j++) {
				costs[(i * energy[0].length) + j] = energy[i][j];
			}
		}

        return path(successors, costs, longueurTableau, longueurTableau + 1);
    }

    /**
     * Draw a seam on an image
     * @param image original image
     * @param seam a seam on this image
     * @return a new image with the seam in blue
     */
    public static int[][] merge(int[][] image, int[] seam) {
        // Copy image
        int width = image[0].length;
        int height = image.length;
        int[][] copy = new int[height][width];
        for (int row = 0; row < height; ++row)
            for (int col = 0; col < width; ++col)
                copy[row][col] = image[row][col];

        // Paint seam in blue
        for (int row = 0; row < height; ++row)
            copy[row][seam[row]] = 0x0000ff;

        return copy;
    }

    /**
     * Remove specified seam
     * @param image original image
     * @param seam a seam on this image
     * @return the new image (width is decreased by 1)
     */
    public static int[][] shrink(int[][] image, int[] seam) {
        int width = image[0].length;
        int height = image.length;
        int[][] result = new int[height][width - 1];
        for (int row = 0; row < height; ++row) {
            for (int col = 0; col < seam[row]; ++col)
                result[row][col] = image[row][col];
            for (int col = seam[row] + 1; col < width; ++col)
                result[row][col - 1] = image[row][col];
        }
        return result;
    }

}
