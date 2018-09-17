package org.witch.bongo.math;

import Jama.Matrix;

public class QuadRectTransform {
	  public QuadRectTransform(PVector q1, PVector q2, PVector q3, PVector q4,
	                   PVector r1, PVector r2, PVector r3, PVector r4) {

	    Matrix A = new Matrix(new double[][]{
	      { r1.x, r1.y, 1., 0., 0., 0., (-q1.x)*r1.x, (-q1.x)*r1.y },
	      { 0., 0., 0., r1.x, r1.y, 1., (-q1.y)*r1.x, (-q1.y)*r1.y },
	      { r2.x, r2.y, 1., 0., 0., 0., (-q2.x)*r2.x, (-q2.x)*r2.y },
	      { 0., 0., 0., r2.x, r2.y, 1., (-q2.y)*r2.x, (-q2.y)*r2.y },
	      { r3.x, r3.y, 1., 0., 0., 0., (-q3.x)*r3.x, (-q3.x)*r3.y },
	      { 0., 0., 0., r3.x, r3.y, 1., (-q3.y)*r3.x, (-q3.y)*r3.y },
	      { r4.x, r4.y, 1., 0., 0., 0., (-q4.x)*r4.x, (-q4.x)*r4.y },
	      { 0., 0., 0., r4.x, r4.y, 1., (-q4.y)*r4.x, (-q4.y)*r4.y }
	    });

	    Matrix B = new Matrix(new double[][]{
	      { q1.x },
	      { q1.y },
	      { q2.x },
	      { q2.y },
	      { q3.x },
	      { q3.y },
	      { q4.x },
	      { q4.y }
	    });

	    Matrix s = A.solve(B);

	    rect2quadMat = new Matrix(new double[][]{
	      { s.get(0, 0), s.get(1, 0), s.get(2, 0) },
	      { s.get(3, 0), s.get(4, 0), s.get(5, 0) },
	      { s.get(6, 0), s.get(7, 0), 1. }
	    });

	    quad2rectMat = rect2quadMat.inverse();
	  }

	  /*
	  Translates a (x, y) quadrilateral point into (X, Y) rectangle point
	  */
	  public PVector quad2rect(PVector v) {
	    return transform(quad2rectMat, v);
	  }

	  /*
	  Translates a (X, Y) rectangle point into (x, y) quadrilateral point
	  */
	  public PVector rect2quad(PVector v) {
	    return transform(rect2quadMat, v);
	  }

	  private PVector transform(Matrix transformMat, PVector v) {
	    Matrix columnVec = new Matrix(new double[][]{
	      { v.x },
	      { v.y },
	      { 1. }
	    });

	    Matrix result = transformMat.times(columnVec);

	    return new PVector(new Float(result.get(0, 0) / result.get(2, 0)),
	                       new Float(result.get(1, 0) / result.get(2, 0)));
	  }


	  private Matrix rect2quadMat;
	  private Matrix quad2rectMat;
	}