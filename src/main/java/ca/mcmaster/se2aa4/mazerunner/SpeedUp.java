package ca.mcmaster.se2aa4.mazerunner;

public class SpeedUp {
    public Float speedUpRatio(Path baseline, Path method){
        Integer baseline_length = getMovementCount(baseline);
        Integer method_length = getMovementCount(method);
        Float speedUp = getRatio(baseline_length, method_length);
        return speedUp;
    }
    public Integer getMovementCount(Path path){
        String length = path.getCanonicalForm().replaceAll(" ", "");
        Integer counter = 0;
        for (int i = 0; i < length.length(); i++){
            if (length.charAt(i) == 'F'){
                counter++;
            }
        }
        return counter;
    }
    public Float getRatio(Integer a, Integer b){
        Float speedup = (float) a/b;
        return speedup;
    }
}
