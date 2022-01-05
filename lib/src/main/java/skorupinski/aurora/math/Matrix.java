package skorupinski.aurora.math;

public class Matrix {
    private float[][] array;
    public int rows, columns;

    public Matrix(float[][] array) {
        this.array = array;
        rows = array.length;
        columns = array[0].length;
    }

    @Override
    public String toString() {
        String s = "";

        for(int i = 0; i < rows; i++) {
            s += "[";
            for(int j = 0; j < columns; j++) {
                s += " " + array[i][j];
                if(j == columns - 1) {
                    s += " ";
                } else {
                    s += ", ";
                }
            }
            s += "]\n";
        }

        return s;
    }

    public Matrix transpose() {
        float[][] transposed = new float[columns][rows];

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                transposed[i][j] = array[j][i];
            }
        }

        return new Matrix(transposed);
    }

    public Matrix add(float n) {
        float[][] result = array.clone();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                result[i][j] += n;
            }
        }

        return new Matrix(result);
    }

    public Matrix add(Matrix m) {
        float[][] result = array.clone();

        if(m.rows != rows || m.columns != columns) {
            throw new IllegalStateException(
                "Matrices should be of the same size."
            );
        } else {
            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < columns; j++) {
                    result[i][j] += m.getArray()[i][j];
                }
            }
        }

        return new Matrix(result);
    }

    public Matrix multiply(float n) {
        float[][] result = array.clone();

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                result[i][j] *= n;
            }
        }

        return new Matrix(result);
    }

    public Matrix multiply(Matrix m) {
        if(columns != m.rows) {
            throw new IllegalStateException(
                "Number of columns of first matrix must equal the number of rows of the second."
            );
        } else {
            float[][] result = new float[rows][m.columns];

            for(int i = 0; i < rows; i++) {
                for(int j = 0; j < m.columns; j++) {
                    float sum = 0;
                    for(int k = 0; k < columns; k++) {
                        sum += array[i][k] * m.getArray()[k][j];
                    }
                    result[i][j] = sum;
                }
            }
            return new Matrix(result);
        }
    }

    private static float changeSign(float n) {
        if (n % 2 == 0) {
            return 1;
        }
        return -1;
    }

    public float determinant() {
		if(rows != columns) {
            throw new IllegalStateException("Matrix should be square.");
        }
        
		if(getSize() == 1){
			return array[0][0];
		}
			
		if(getSize() == 2) {
			return (array[0][0] * array[1][1]) - (array[0][1] * array[1][0]);
		}
		float sum = 0;
		for(int i = 0; i < columns; i++) {
            Matrix submatrix = submatrix(0, i);
			sum += changeSign(i) * array[0][i] * submatrix.determinant();
		}
		return sum;
	}

    public Matrix submatrix(int excludedRow, int excludedColumn) {
        float[][] submatrix = new float[rows - 1][rows - 1];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; i != excludedRow && j < columns; j++) {
                if (j != excludedColumn) { 
                    int x = i - 1;
                    if(i < excludedRow) {
                        x = i;
                    }
                    int y = j - 1;
                    if(j < excludedColumn) {
                        y = j;
                    }

                    submatrix[x][y] = array[i][j];
                }
        
            }
        }
        return new Matrix(submatrix);
    }

    public Matrix cofactor() {
		float[][] result = new float[rows][columns];
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
                Matrix submatrix = submatrix(i, j);
                result[i][j] = changeSign(i) * changeSign(j) * submatrix.determinant();
            }
		}
		
		return new Matrix(result);
	}

    public Matrix inverse() {
        Matrix cofactor = cofactor();
        Matrix t = cofactor.transpose();

		return t.multiply(1.0f / determinant());
	}

    public float[][] getArray() {
        return array;
    }

    public int getSize() {
        return rows * columns;
    }

    public float getValueAt(int x, int y) {
        return array[x][y];
    }
} 