package view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JPanel;

public class TalkPanel extends JPanel implements Runnable {
    public static boolean issleep = true;
    public static int windows = 2;
    public static int index = 1;//页数，按一下空格翻到下一页
    String str1 = " 想要增加你的能力吗?";
    String str21 = "如果你有25个金币,";
    String str22 = "如果你有足够的经验,";
    String str3 = "你可以任意选择一项:";
    String str4 = "上下键选择，空格键确认";
    public static int loca = 1;//光标默认指向第一行

    public void paint(Graphics g) {
        super.paint(g);
        setBackground(new Color(100, 100, 100));
        this.setBounds(450, 80, 400, 400);
        g.setFont(new Font("宋体", Font.BOLD, 26));//字号设置
        g.setColor(new Color(255, 255, 255));

        if (windows == 1 || windows == 2) {//垃圾商人专用
            g.drawString(str1, 200 - str1.length() * 13, 30);
            if (windows == 1) {
                g.drawString(str21, 200 - str21.length() * 13, 65);
                g.drawString("增加800点生命", 200 - "增加800点生命".length() * 13, 185);//225-length*汉字/2
                g.drawString("增加4点攻击", 200 - "增加4点攻击".length() * 13, 240);
                g.drawString("增加4点防御", 200 - "增加4点防御".length() * 13, 310);
            } else if (windows == 2) {
                g.drawString(str22, 200 - str22.length() * 13, 65);
                g.drawString("100经验升一级", 200 - "100经验升一级".length() * 13, 185);
                g.drawString("30经验增加5点攻击", 200 - "30经验增加5点攻击".length() * 13, 240);
                g.drawString("30经验增加5点防御", 200 - "30经验增加5点防御".length() * 13, 310);
            }
            g.drawString(str3, 200 - str3.length() * 13, 100);
            g.drawString(str4, 200 - str4.length() * 13, 135);
            g.drawString("退出", 200 - "退出".length() * 13, 370);
            int y = 153;
            if (loca == 1) {
                y = 153;
            } else if (loca == 2) {
                y = 208;
            } else if (loca == 3) {
                y = 278;
            } else if (loca == 4) {
                y = 338;
            }
            g.drawRect(20, y, 360, 40);//选项的矩形方框
        } else if (windows == 100 || windows == 200) {//高级商人专用
            g.drawString(str1, 200 - str1.length() * 13, 30);
            if (windows == 100) {
                g.drawString("如果你有100个金币", 200 - "如果你有100个金币".length() * 13, 65);
                g.drawString("增加4000点生命", 200 - "增加4000点生命".length() * 13, 185);//225-length*汉字/2
                g.drawString("增加20点攻击", 200 - "增加20点攻击".length() * 13, 240);
                g.drawString("增加20点防御", 200 - "增加20点防御".length() * 13, 310);
            } else if (windows == 200) {
                g.drawString(str22, 200 - str22.length() * 13, 65);
                g.drawString("270经验升三级", 200 - "270经验升三级".length() * 13, 185);
                g.drawString("95经验增加17点攻击", 200 - "95经验增加17点攻击".length() * 13, 240);
                g.drawString("95经验增加17点防御", 200 - "95经验增加17点防御".length() * 13, 310);
            }
            g.drawString(str3, 200 - str3.length() * 13, 100);
            g.drawString(str4, 200 - str4.length() * 13, 135);
            g.drawString("退出", 200 - "退出".length() * 13, 370);
            int y = 153;
            if (loca == 1) {
                y = 153;
            } else if (loca == 2) {
                y = 208;
            } else if (loca == 3) {
                y = 278;
            } else if (loca == 4) {
                y = 338;
            }
            g.drawRect(20, y, 360, 40);//选项的矩形方框
        } else if (windows == 3) {//用金币换取钥匙
            g.drawString("如果你有足够的金币", 200 - "如果你有足够的金币".length() * 13, 30);
            g.drawString("我可以卖你一些钥匙", 200 - "我可以卖你一些钥匙".length() * 13, 65);
            g.drawString("你可以任选一种", 200 - "你可以任选一种".length() * 13, 100);
            g.drawString("10金币买黄钥匙", 200 - "10金币买黄钥匙".length() * 13, 185);
            g.drawString("50金币买蓝钥匙", 200 - "50金币买蓝钥匙".length() * 13, 240);
            g.drawString("100金币买红钥匙", 200 - "100金币买红钥匙".length() * 13, 310);
            g.drawString("退出", 200 - "退出".length() * 13, 370);
            int y = 153;
            if (loca == 1) {
                y = 153;
            } else if (loca == 2) {
                y = 208;
            } else if (loca == 3) {
                y = 278;
            } else if (loca == 4) {
                y = 338;
            }
            g.drawRect(20, y, 360, 40);//选项的矩形方框
        } else if (windows == 300) {//用钥匙换金币
            g.drawString("如果你需要一些金币", 200 - "如果你需要一些金币".length() * 13, 30);
            g.drawString("我可以回收一些钥匙", 200 - "我可以回收一些钥匙".length() * 13, 65);
            g.drawString("你可以任选一种", 200 - "你可以任选一种".length() * 13, 100);
            g.drawString("7金币回收一把黄钥匙", 200 - "7金币回收一把黄钥匙".length() * 13, 185);
            g.drawString("35金币回收一把蓝钥匙", 200 - "35金币回收一把蓝钥匙".length() * 13, 240);
            g.drawString("70金币回收一把红钥匙", 200 - "70金币回收一把红钥匙".length() * 13, 310);
            g.drawString("退出", 200 - "退出".length() * 13, 370);
            int y = 153;
            if (loca == 1) {
                y = 153;
            } else if (loca == 2) {
                y = 208;
            } else if (loca == 3) {
                y = 278;
            } else if (loca == 4) {
                y = 338;
            }
            g.drawRect(20, y, 360, 40);//选项的矩形方框
        } else if (windows == 4) {//怪物手册
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为100
            g.drawString("恭喜你获得怪物手册", 200 - "恭喜你获得怪物手册".length() * 13, 30);
            g.drawString("按L键可查看和关闭怪物信息", 200 - "按L键可查看和关闭怪物信息".length() * 13, 65);
            g.drawString("按任意键确认", 200 - "按任意键确认".length() * 13, 100);
            g.drawString("确定", 200 - "确定".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 5) {//小偷开启二楼的门  5--》6   找到小偷先判断有没有铁镐有win7，没有win
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为100
            g.drawString("非常感谢，我终于自由了", 200 - "非常感谢，我终于自由了".length() * 13, 30);
            g.drawString("为了报答你", 200 - "为了报答你".length() * 13, 65);
            g.drawString("一会儿我会把第二层的暗门打开", 200 - "一会儿我会把第二层的暗门打开".length() * 13, 100);
            g.drawString("确定", 200 - "确定".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 500) {//小偷开启二楼的门  5--》6   找到小偷先判断有没有铁镐有win7，没有win
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为100
            g.drawString("对了", 200 - "对了".length() * 13, 30);
            g.drawString("要是你帮我找到我的铁镐", 200 - "要是你帮我找到我的铁镐".length() * 13, 65);
            g.drawString("我就能帮你打通18层的隧道", 200 - "我就能帮你打通18层的隧道".length() * 13, 100);
            g.drawString("确定", 200 - "确定".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 5000) {//找到了铁锹
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为100
            g.drawString("太好了，你找到他了！", 200 - "太好了，你找到他了！".length() * 13, 30);
            g.drawString("我现在就帮你挖通18层的隧道", 200 - "我现在就帮你挖通18层的隧道".length() * 13, 65);
            //g.drawString("我就能帮你打通18层的隧道", 200-"我就能帮你打通18层的隧道".length()*13, 100);
            g.drawString("确定", 200 - "确定".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 6) {//风之罗盘
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为100
            g.drawString("恭喜你获得风之罗盘", 200 - "恭喜你获得风之罗盘".length() * 13, 30);
            g.drawString("按J键选择想去的楼层", 200 - "按J键选择想去的楼层".length() * 13, 65);
            g.drawString("按空格键确认", 200 - "按空格键确认".length() * 13, 100);
            g.drawString("确定", 200 - "确定".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 7) {//15层的男老人，加攻击120
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为100
            g.drawString("你好年轻人，我这有个上等武器", 200 - "你好年轻人，我这有个上等武器".length() * 13, 30);
            g.drawString("只要你出500经验我就卖给你", 200 - "只要你出500经验我就卖给你".length() * 13, 65);
            g.drawString("按空格键确认", 200 - "按空格键确认".length() * 13, 100);
            g.drawString("确定", 200 - "确定".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 700) {//15层的女老人，加防御120
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为100
            g.drawString("你好年轻人，我这有个上等防具", 200 - "你好年轻人，我这有个上等防具".length() * 13, 30);
            g.drawString("只要你出500金币我就卖给你", 200 - "只要你出500金币我就卖给你".length() * 13, 65);
            g.drawString("按空格键确认", 200 - "按空格键确认".length() * 13, 100);
            g.drawString("确定", 200 - "确定".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 7000) {//2层的男老人，钢剑加攻击----30
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为
            g.drawString("哦，我的孩子，真是太谢谢你了", 200 - "哦，我的孩子，真是太谢谢你了".length() * 13, 30);
            g.drawString("这个东西就送你吧", 200 - "这个东西就送你吧".length() * 13, 65);
            g.drawString("这是我年轻的时候用的", 200 - "这是我年轻的时候用的".length() * 13, 100);
            g.drawString("确定", 200 - "确定".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 70000) {//2层的女老人，钢盾加防御----30
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为
            g.drawString("哦老天，真是太感谢你了", 200 - "哦老天，真是太感谢你了".length() * 13, 30);
            g.drawString("为了表达我的谢意", 200 - "为了表达我的谢意".length() * 13, 65);
            g.drawString("这个东西就送给你了", 200 - "这个东西就送给你了".length() * 13, 100);
            g.drawString("确定", 200 - "确定".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 8) {//18层的公主
            g.setFont(new Font("宋体", Font.BOLD, 25));//字号设置为100
            g.drawString("啊，你是来救我的吗", 200 - "啊，你是来救我的吗".length() * 13, 30);
            g.drawString("可是我还不想走", 200 - "可是我还不想走".length() * 13, 65);
            g.drawString("我想亲眼看着大魔王被杀死", 200 - "我想亲眼看着大魔王被杀死".length() * 13, 100);
            g.drawString("好的", 200 - "好的".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 9) {//0层的仙女
            g.setFont(new Font("宋体", Font.BOLD, 23));//字号设置为100
            g.drawString("我是这里的仙子，公主就在里面", 200 - "我是这里的仙子，公主就在里面".length() * 13, 30);
            g.drawString("我可以给你提升一些力量", 200 - "我可以给你提升一些力量".length() * 13, 65);
            g.drawString("不过你得先帮我找到第七层的十字架", 200 - "不过你得先帮我找到第七层的十字架".length() * 13, 100);
            g.drawString("好的", 200 - "好的".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 90) {//0层的仙女
            g.setFont(new Font("宋体", Font.BOLD, 24));//字号设置为100
            g.drawString("找到后你来这里找我", 200 - "找到后你来这里找我".length() * 12, 30);
            g.drawString("我就能把力量借给你去救公主了", 200 - "我就能把力量借给你去救公主了".length() * 12, 65);
            g.drawString("并且帮你打开第21层的门", 200 - "并且帮你打开第21层的门".length() * 13, 100);
            g.drawString("好的", 200 - "好的".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        } else if (windows == 900) {//找到了十字架
            g.setFont(new Font("宋体", Font.BOLD, 24));//字号设置为100
            g.drawString("你找到它啦！我现在就把力量传输给你", 200 - "你找到它啦！我现在就把力量传输给你".length() * 12, 30);
            g.drawString("妈咪妈咪哄！", 200 - "妈咪妈咪哄！".length() * 12, 65);
            g.drawString("好了，快去救你的公主吧", 200 - "好了，快去救你的公主吧".length() * 13, 100);
            g.drawString("好的", 200 - "好的".length() * 13, 185);
            g.drawRect(20, 153, 360, 40);//选项的矩形方框
        }
    }

    public void run() {
        while (true) {
            try {
                while (issleep) {
                    Thread.sleep(1000);
                }
                Thread.sleep(40);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            this.repaint();
        }
    }
}
