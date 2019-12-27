package tests.tetris;

import com.mygdx.game.objects.Field;
import com.mygdx.game.objects.Figure;
import static com.mygdx.game.objects.Figure.type.I;
import static com.mygdx.game.objects.Figure.type.L;
import static com.mygdx.game.objects.Figure.type.S;
import static com.mygdx.game.objects.Figure.type.T;

import static org.junit.Assert.assertEquals;

import org.junit.Assert;
import org.junit.Test;

public class TetrisTests {

	@Test
	public void oneEqualsOne() {
		assertEquals(1, 1);
	}

	@Test
	public void testCheckFullLine() {
		int[][] fieldMatrix = new int[][] {
				new int[] {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
				new int[] {1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		};

		Field field = new Field(fieldMatrix);
		Figure figure = new Figure(0, 6, I);
		field.print();
		field.update(figure);
		field.print();
		Assert.assertTrue(field.checkFullLine());
		field.print();
		int[][] expected1 = new int[][] {
				new int[] {1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 0, 1, 1, 1, 1, 1, 1, 1, 1},
				new int[] {0, 0, 0, 0, 0, 0, 0, 1, 1, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
				new int[] {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
		};
		Assert.assertArrayEquals(expected1, field.getMatrix());
	}

	@Test
	public void testFigureRotate() {
		Figure figure = new Figure(0, 0, L);
		figure.rotate();
		figure.printMatrix();
		int[][] expected = new int[][] {
				new int[]{0, 0, 1, 0},
				new int[]{1, 1, 1, 0},
				new int[]{0, 0, 0, 0},
				new int[]{0, 0, 0, 0},
		};
		Assert.assertArrayEquals(expected, figure.getMatrix());
	}

	@Test
	public void testInBorders(){
		Figure figure = new Figure(15, 21);
		Assert.assertFalse(figure.inBorders(0));
		Figure figure2 = new Figure(15, 15);
		Assert.assertTrue(figure2.inBorders(0));
		Figure figure3 = new Figure(15, 18, I);
		figure3.rotate();
		Assert.assertFalse(figure3.inBorders(0));
		Figure figure4 = new Figure(15, 0, I);
		figure4.rotate();
		Assert.assertFalse(figure4.inBorders(-1));
		Figure figure5 = new Figure(15, 0, I);
		figure5.rotate();
		Assert.assertTrue(figure5.inBorders(0));
	}

	@Test
	public void testShiftElems() {
		Figure figure = new Figure(10, 10, I);
		//figure.printMatrix();
		figure.shiftElems(1, figure.getMatrix());
		int[][] expected = new int[][] {
				new int[] {1, 1, 1, 1},
				new int[] {0, 0, 0, 0},
				new int[] {0, 0, 0, 0},
				new int[] {0, 0, 0, 0},
		};
		//Assert.assertArrayEquals(expected, figure.getMatrix());

		int[][] actual = new int[][] {
				new int[] {1, 1, 1, 1},
				new int[] {0, 0, 0, 0},
				new int[] {0, 0, 0, 0},
				new int[] {0, 0, 0, 0},
		};
		figure.shiftElems(1, actual);
		figure.printMatrix(actual);
		Assert.assertArrayEquals(expected, actual);

		figure = new Figure(10, 10, I);
		//figure.rotate();
		actual = new int[][] {
				new int[] {1, 1, 1, 1},
				new int[] {0, 0, 0, 0},
				new int[] {0, 0, 0, 0},
				new int[] {0, 0, 0, 0},
		};
		figure.shiftElems(2, actual);
		expected = new int[][] {
				new int[] {0, 0, 0, 0},
				new int[] {1, 1, 1, 1},
				new int[] {0, 0, 0, 0},
				new int[] {0, 0, 0, 0},
		};
		figure.printMatrix(actual);
		Assert.assertArrayEquals(expected, actual);
		figure = new Figure(10, 10, T);
		figure.shiftElems(1, figure.getMatrix());
		expected = new int[][] {
				new int[] {0, 0, 1, 0},
				new int[] {0, 1, 1, 1},
				new int[] {0, 0, 0, 0},
				new int[] {0, 0, 0, 0},
		};
		Assert.assertArrayEquals(expected, figure.getMatrix());
	}

	@Test
	public void testFigureMirror(){
		Figure figure = new Figure(5, 5, S);
		int[][] expected = new int[][] {
				new int[] {1, 1, 0, 0},
				new int[] {0, 1, 1, 0},
				new int[] {0, 0, 0, 0},
				new int[] {0, 0, 0, 0},
		};
		figure.mirror();
		figure.printMatrix();
	}
}
