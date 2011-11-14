
package Implementation;

import Interface.ITopologyObject;

public class CoordinateTopologyObjectImpl implements ITopologyObject{

    private double x;
    private double y;

    public CoordinateTopologyObjectImpl(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Object read(String source) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public String write() {
        String value = x + ", " + y;
        return value;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

}
