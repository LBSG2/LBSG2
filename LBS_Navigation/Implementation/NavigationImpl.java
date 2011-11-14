
package Implementation;

import Interface.INavigation;
import Interface.ITopology;
import Interface.ITopologyObject;

public class NavigationImpl implements INavigation {

    private ITopology topology;

    public NavigationImpl(){
        this.topology = new TopologyImpl();
    }

    public Object[] executeNavigation() {
        ITopologyObject[] pointsOfInterest = readPointsOfInterest();
        topology.setPointsOfInterest(pointsOfInterest);
        
        return null;
    }

    private ITopologyObject[] readPointsOfInterest(){
        return null;
    }

}
