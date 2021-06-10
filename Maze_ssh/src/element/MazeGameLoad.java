package element;

import java.io.*;
import java.util.Properties;
import javax.swing.ImageIcon;

import controller.Auto;

public class MazeGameLoad {
    private static int pictureNum = 200;
    private static Manager em = null;//获取资源管理器
    public static final ImageIcon[] imgArr = new ImageIcon[pictureNum];

    public static void createMan(int x, int y) {
        Man man = Man.createMan(x, y);
        Manager.getManager(1).getManList().add(man);
    }

    /**
     * 此函数用于怪物手册的去重复功能
     *
     * @param type
     * @return 因为enemy重写equals和hashcode方法将出现显示错误，所以只能根据怪物type来判断，
     * 将当前层所有怪物type放进set不重复集合中，最后再取出所有type，调用此函数返回Enemy类型，
     * 从而得到这种怪物的详细信息
     */
    public static Enemy getEnemyByType(int type) {
        return new Enemy(0, 0, imgArr[type], type);
    }

    public static void mapLoad(int key, String res) {
        System.out.println("mapload加载" + key + "地图");

        em = Manager.getManager(key);
        if (imgArr[1] == null) {
            for (int i = 1; i <= 50; i++) {
                imgArr[i] = new ImageIcon("src/data/obj/" + i + ".jpg");
            }
            for (int i = 113; i < pictureNum; i++) {
                imgArr[i] = new ImageIcon("src/data/change/" + i + ".jpg");
            }
        }
        //判断此关卡是否已经被加载过了
        boolean f = false;
        if (!Auto.isLoad[key]) {
            Auto.isLoad[key] = true;
            System.out.println("加载第" + key + "关");
            loadPro(key, res);
        }
    }

    private static Properties ps = new Properties();

    private static void loadPro(int key, String res) {
        //自己重新写读取文件的函数
        try {
            String url = res + key + ".map";
            File file = new File("src/data/pro/" + key + ".map");
            InputStream in = new FileInputStream(file);
            BufferedReader bis = new BufferedReader(new InputStreamReader(in));
            String readLine = bis.readLine();
            Manager.getManager(Auto.getKey()).thingnum = new int[11][11];
            System.out.println("loadPro  " + url);
            while ((readLine = bis.readLine()) != null) {
                if (readLine.equals("")) continue;
                String[] indexname = readLine.split("=");
                int name = Integer.parseInt(indexname[0]);
                String[] local = indexname[1].split(";");
                for (int i = 0; i < local.length; i++) {//每一对坐标
                    String s = local[i];//取出每个坐标对   100,20
                    String arrS[] = s.split(","); //{"100","20"}  //有x，y，类型
                    int x = Integer.parseInt(arrS[0]);//横坐标
                    int y = Integer.parseInt(arrS[1]);//纵坐标
                    (Manager.getManager(Auto.getKey()).thingnum[x][y])++;
                    if (Manager.getManager(Auto.getKey()).thingnum[x][y] == 2) {
                        System.out.print(em.getThings()[x][y] + "----");
                        Manager.getManager(Auto.getKey()).getThings()[x][y] = name;
                        System.out.println(em.getThings()[x][y]);
                    } else {
                        Manager.getManager(Auto.getKey()).getThings()[x][y] = name;
                    }
                    if (name > 100) {//类型大于100
                        em.getEnemyList().add(new Enemy(x, y, imgArr[name], name));
                        if (name == 159)
                            System.out.println("创建了一个怪物对象" + imgArr[name]);
                        //System.out.println("创建了一个怪物对象"+name+": "+x+","+y);
                    } else {
                        if (Man.haveCross == 2 && Auto.getKey() == 20 && x == 5 && y == 7 && name == 11) {//还了十字架，那么加载20层的时候
                            System.out.println("没有去过20层");
                            em.getObjList().add(new Obj(x, y, imgArr[16], 16));
                            Manager.getManager(Auto.getKey()).getThings()[x][y] = 16;
                        } else
                            em.getObjList().add(new Obj(x, y, imgArr[name], name));
                        //System.out.println("创建了一个物品");
                    }
                }
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
