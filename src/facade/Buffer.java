package facade;

import java.util.ArrayList;
import java.util.List;

class Buffer {
    private char[] characters;
    private int lineWidth;

    public Buffer(int lineHeight, int lineWidth){
        this.lineWidth = lineWidth;
        characters = new char[lineHeight*lineWidth]; //data 를 넣을 크기
    }
    public char charAt(int x, int y){ //low level 의 construct
        return characters[y*lineWidth + x];
    }

}
class Viewport{
    private Buffer buffer;
    private int width;
    private int height;
    private int offsetX;
    private int offsetY;

    public Viewport(Buffer buffer , int width, int height,
                    int offsetX, int offsetY){
        this.buffer = buffer;
        this.width=width;
        this.height=height;
        this.offsetX = offsetX;
        this.offsetY= offsetY;
    }
    public char charAt(int x, int y){
        return buffer.charAt(x + offsetX,y + offsetY);
    }
}
class Console{
    private List<Viewport> viewports = new ArrayList<>();
    int width, height;

    public Console(int width, int height) {
        this.width = width;
        this.height = height;
    }

    //api를 단순하게 한다. 숨긴다
    public static Console newConsole(int width, int height){
        Buffer buffer = new Buffer(width, height);
        Viewport viewport = new Viewport(buffer, width, height, 0,0);
        Console console = new Console(width,height);
        console.addViewPort(viewport);
        return console;
    }
    public void addViewPort(Viewport viewport){
        this.viewports.add(viewport);
    }
    public void render(){
        for (int y=0; y<height ; y++){
            for (int x=0;x<width ;x++){
                for(Viewport v: viewports){
                    System.out.print(v.charAt(x,y));
                }
            }
            System.out.println();
        }
    }
}

class Demo{
    public static void main(String[] args) {
//        Buffer buffer = new Buffer(30,20);
//        Viewport viewport = new Viewport(buffer, 30,20,0,0);
//        Console console = new Console(30, 20);
//        console.addViewPort(viewport);

        Console console = Console.newConsole(30,20);
        console.render();
    }
}