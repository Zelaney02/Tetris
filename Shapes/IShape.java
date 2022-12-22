/*
* block drawing
*/ 

public class IShape extends TetrisBlock {
  public IShape() {
      // super - makes parent structures accessible
      super( new int[][]{ {1, 1, 1, 1} } );
  }
  // IShape gets it's own rotation because it looks weird otherwise

  @Override
  public void rotate() {
    super.rotate();
    // more sharp and clean rotations
    if ( this.getWidth() == 1) {
      this.setX( this.getX() + 1 );
      this.setY( this.getY() - 1 );
      
    }
        
    else {
      this.setX( this.getX() - 1 );
      this.setY( this.getY() + 1 );
    }
  }
}