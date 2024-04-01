package ca.mcmaster.se2aa4.mazerunner;

public class SpeedUp {
    public Float speedUp(Path baseline, Path method){
        Integer baseline_length = getPathLength(baseline);
        Integer method_length = getPathLength(method);
        Float speedUp = getRatio(baseline_length, method_length);
        return speedUp;
    }
    public Integer getPathLength(Path path){
        Integer length = path.getCanonicalForm().replaceAll(" ", "").length();
        return length;
    }
    public Float getRatio(Integer a, Integer b){
        Float speedup = (float) a/b;
        return speedup;
    }
}
