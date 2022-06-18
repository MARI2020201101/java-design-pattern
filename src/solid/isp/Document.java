package solid.isp;

class Document {
}

interface Machine{
    void print(Document d);
    void fax(Document d); //인터페이스 구현하는 클라이언트 - 구현을 강제당한다
    void scan(Document d);
}

//interface segregation principle
//extra 한 메서드를 만들지 말라
interface Printer{
    void print(Document d);
}
interface Scanner{
    void scan(Document d);
}

class JustAPrinter implements Printer {
    @Override
    public void print(Document d) {

    }
}
class Photocopier implements Printer, Scanner{
//이런식으로 둘다 구현하면됨!!
    @Override
    public void print(Document d) {
        //데코레이터 패턴을 쓸수도있음
    }

    @Override
    public void scan(Document d) {
    }
}

class MultiFunctionMachine implements Printer,Scanner{
//데코레이터 패턴사용하기
    private Printer printer;
    private Scanner scanner;
    @Override
    public void print(Document d) {
        printer.print(d);
    }

    @Override
    public void scan(Document d) {
        scanner.scan(d);
    }
}

class MultiFunctionPrinter implements Machine{

    @Override
    public void print(Document d) {

    }

    @Override
    public void fax(Document d) {

    }

    @Override
    public void scan(Document d) {

    }
}
class OldFashionPrinter implements Machine{

    @Override
    public void print(Document d) {
        //can print
    }
//구현을 강제당한다
    @Override
    public void fax(Document d) {
        //cannot fax -> 무엇을 채워야 하나?
    }

    @Override
    public void scan(Document d) {
        //cannot scan
        //1 . 아무것도 안하기
        //2 . Exception 터뜨리기
    }
}