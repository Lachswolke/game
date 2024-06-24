import GameObjects.ImmovableObjects.Grass;
import GameObjects.ImmovableObjects.ImmovableObject;
import GameObjects.ImmovableObjects.Moonstone;

import java.util.ArrayList;
import java.util.Random;

public class TerrainGeneration {
    protected int amountOfBlocks;
    protected int widthWindow;
    protected int heightWindow;
    public TerrainGeneration(int amountOfBlocks, int widthWindow, int heightWindow){
        this.amountOfBlocks = amountOfBlocks;
        this.heightWindow = heightWindow;
        this.widthWindow = widthWindow;
    }

    public ArrayList<ImmovableObject> generateTerrain(){
        ArrayList<ImmovableObject> blocks = new ArrayList<>();
        Random rn = new Random();
        for (int i = 0; i < amountOfBlocks; i++) {
            blocks.add(new Moonstone(rn.nextInt(widthWindow), rn.nextInt(heightWindow), 100,100));
        }
        return blocks;

    }


}
