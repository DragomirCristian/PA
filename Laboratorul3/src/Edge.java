/**
 * @author Botez Mihai
 * @author Dragomir Cristian
 */
public class Edge {
    private Node node1;
    private Node node2;
    private boolean type;
    private int cost;

    public Edge(Node node1, Node node2, int cost) {
        this.node1 = node1;
        this.node2 = node2;
        this.cost = cost;
        this.type = true;
    }

    public Edge(Node node1, Node node2, int cost, boolean type) {
        this.node1 = node1;
        this.node2 = node2;
        this.type = type;
        this.cost = cost;
    }

    public Node getNode1() {
        return node1;
    }

    public void setNode1(Node node1) {
        this.node1 = node1;
    }

    public Node getNode2() {
        return node2;
    }

    public void setNode2(Node node2) {
        this.node2 = node2;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
}
