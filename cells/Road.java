package cells;

public class Road extends Cell{                 // This class is help us about roads.
    public Road(int x, int y) {
        super(x,y,'R');
    }

    @Override
    public boolean isConnectable() {
        return true;
    }
}
