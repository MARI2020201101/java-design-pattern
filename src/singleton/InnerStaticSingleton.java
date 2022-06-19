package singleton;

class InnerStaticSingleton {
    private InnerStaticSingleton(){}
    private static class Impl{
        private static final InnerStaticSingleton INSTANCE = new InnerStaticSingleton();
    }
    //thread safety 를 걱정하지 않아도 된다는 장점이 있다.
    public InnerStaticSingleton getInstance(){
        return Impl.INSTANCE;
    }
}
