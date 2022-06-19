package builder;

enum Almonds{
    CRUNCH,CHUNKY,GRIND;
}
class AlmondChoco extends ChocoV2{
    private Almonds almonds;

    public static AlmondChocoBuilder builder(){
        return new AlmondChocoBuilder();
    }

    @Override
    public String toString() {
        return "AlmondChoco{" +
                "almonds=" + almonds +
                ", cacao=" + cacao +
                ", isNatural=" + isNatural +
                '}';
    }

    static class AlmondChocoBuilder extends ChocoV2.ChocoBuilder<AlmondChocoBuilder>{
        private AlmondChoco almondChoco;

        public AlmondChocoBuilder(){
            almondChoco = new AlmondChoco();
        }

        public AlmondChocoBuilder almond(Almonds almonds){

            almondChoco.almonds = almonds;
            return self();
        }
        @Override
        protected AlmondChocoBuilder self() {
            return this;
        }

        @Override
        public ChocoV2 build() {
            return almondChoco;
        }
    }
}
class AlmondChocoBuilderTest{
    public static void main(String[] args) {
        ChocoV2 almondChoco = AlmondChoco.builder()
                .cacao(99)
                .isNatural(true)
                .almond(Almonds.CHUNKY)
                .build();
        System.out.println(almondChoco);
    }
}