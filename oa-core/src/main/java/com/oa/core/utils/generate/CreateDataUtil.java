package com.oa.core.utils.generate;

import java.io.UnsupportedEncodingException;
import java.util.Random;

/**
 * Created by [Zy]
 * 2017/8/10 11:33
 */
public class CreateDataUtil {

    private CreateDataUtil() {}

    public static String createUserName() {
        return surnameGenerate() + nameGenerate();
    }

    /**
     * 随机生成名字，随机一位或两位
     * @return 名字
     */
    public static String nameGenerate() {
        Random random = new Random();
        int length = random.nextInt(2)+1;
        String name = "";

        if (length == 1) {
            name += charGenerate();
        } else {
            for (int i=0; i<length; i++) {
                name += charGenerate();
            }
        }

        return name;
    }

    /**
     * 随机生成姓氏
     * @return 姓氏
     */
    public static String surnameGenerate() {

        String[] surnames = {"赵", "钱", "孙", "李", "周", "吴", "郑", "王",
                "冯", "陈", "楮", "卫", "蒋", "沈", "韩", "杨",
                "朱", "秦", "尤", "许", "何", "吕", "施", "张",
                "孔", "曹", "严", "华", "金", "魏", "陶", "姜",
                "戚", "谢", "邹", "喻", "柏", "水", "窦", "章",
                "云", "苏", "潘", "葛", "奚", "范", "彭", "郎",
                "鲁", "韦", "昌", "马", "苗", "凤", "花", "方",
                "俞", "任", "袁", "柳", "酆", "鲍", "史", "唐",
                "费", "廉", "岑", "薛", "雷", "贺", "倪", "汤",
                "滕", "殷", "罗", "毕", "郝", "邬", "安", "常",
                "乐", "于", "时", "傅", "皮", "卞", "齐", "康",
                "伍", "余", "元", "卜", "顾", "孟", "平", "黄",
                "和", "穆", "萧", "尹", "姚", "邵", "湛", "汪",
                "祁", "毛", "禹", "狄", "米", "贝", "明", "臧",
                "计", "伏", "成", "戴", "谈", "宋", "茅", "庞",
                "熊", "纪", "舒", "屈", "项", "祝", "董", "梁",
                "杜", "阮", "蓝", "闽", "席", "季", "麻", "强",
                "贾", "路", "娄", "危", "江", "童", "颜", "郭",
                "梅", "盛", "林", "刁", "锺", "徐", "丘", "骆",
                "高", "夏", "蔡", "田", "樊", "胡", "凌", "霍",
                "虞", "万", "支", "柯", "昝", "管", "卢", "莫",
                "经", "房", "裘", "缪", "干", "解", "应", "宗",
                "丁", "宣", "贲", "邓", "郁", "单", "杭", "洪",
                "包", "诸", "左", "石", "崔", "吉", "钮", "龚",
                "程", "嵇", "邢", "滑", "裴", "陆", "荣", "翁",
                "荀", "羊", "於", "惠", "甄", "麹", "家", "封",
                "芮", "羿", "储", "靳", "汲", "邴", "糜", "松",
                "井", "段", "富", "巫", "乌", "焦", "巴", "弓",
                "牧", "隗", "山", "谷", "车", "侯", "宓", "蓬",
                "全", "郗", "班", "仰", "秋", "仲", "伊", "宫",
                "宁", "仇", "栾", "暴", "甘", "斜", "厉", "戎",
                "祖", "武", "符", "刘", "景", "詹", "束", "龙",
                "叶", "幸", "司", "韶", "郜", "黎", "蓟", "薄",
                "印", "宿", "白", "怀", "蒲", "邰", "从", "鄂",
                "索", "咸", "籍", "赖", "卓", "蔺", "屠", "蒙",
                "池", "乔", "阴", "郁", "胥", "能", "苍", "双",
                "闻", "莘", "党", "翟", "谭", "贡", "劳", "逄",
                "姬", "申", "扶", "堵", "冉", "宰", "郦", "雍",
                "郤", "璩", "桑", "桂", "濮", "牛", "寿", "通",
                "边", "扈", "燕", "冀", "郏", "浦", "尚", "农",
                "温", "别", "庄", "晏", "柴", "瞿", "阎", "充",
                "慕", "连", "茹", "习", "宦", "艾", "鱼", "容",
                "向", "古", "易", "慎", "戈", "廖", "庾", "终",
                "暨", "居", "衡", "步", "都", "耿", "满", "弘",
                "匡", "国", "文", "寇", "广", "禄", "阙", "东",
                "欧", "殳", "沃", "利", "蔚", "越", "夔", "隆",
                "师", "巩", "厍", "聂", "晁", "勾", "敖", "融",
                "冷", "訾", "辛", "阚", "那", "简", "饶", "空",
                "曾", "毋", "沙", "乜", "养", "鞠", "须", "丰",
                "巢", "关", "蒯", "相", "查", "后", "荆", "红",
                "游", "竺", "权", "逑", "盖", "益", "桓", "公",
                "万俟", "司马", "上官", "欧阳", "夏侯", "诸葛",
                "闻人", "东方", "赫连", "皇甫", "尉迟", "公羊",
                "澹台", "公冶", "宗政", "濮阳", "淳于", "单于",
                "太叔", "申屠", "公孙", "仲孙", "轩辕", "令狐",
                "锺离", "宇文", "长孙", "慕容", "鲜于", "闾丘",
                "司徒", "司空", "丌官", "司寇", "子车", "颛孙",
                "端木", "巫马", "公西", "漆雕", "乐正", "壤驷",
                "公良", "拓拔", "夹谷", "宰父", "谷梁", "段干",
                "百里", "东郭","南门", "梁丘", "左丘", "东门",
                "西门", "南宫", "微生", "呼延", "羊舌",
                "仉", "督", "晋", "楚", "阎", "法", "汝", "鄢",
                "涂", "钦", "归", "海", "岳", "帅", "缑", "亢",
                "况", "后", "有", "琴", "商", "牟", "佘", "佴",
                "伯", "赏", "墨", "哈", "谯", "笪", "年", "爱",
                "阳", "佟"};

        Integer index = new Random().nextInt(surnames.length -1);

        return surnames[index];
    }


    /**
     * 生成一个汉字
     * @return 一个汉字
     */
    public static String charGenerate() {
        Random random = new Random();

        int hightPos, lowPos; // 定义高低位

        hightPos = (176 + Math.abs(random.nextInt(39)));//获取高位值

        lowPos = (161 + Math.abs(random.nextInt(93)));//获取低位值

        byte[] b = new byte[2];

        b[0] = (new Integer(hightPos).byteValue());

        b[1] = (new Integer(lowPos).byteValue());

        // 转成中文
        String str = null;
        try {
            str = new String(b, "GBk");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return str;
    }


    /**
     * 生成年份
     * @param max 最大不能超过哪一年
     * @param min 最小不能小于当一年
     * @return 返回随机年份
     */
    public static Integer yearGenerate(int max, int min) {
        Random random = new Random();
        Integer tempMaxRand = random.nextInt(max);
        Integer tempMinRand = random.nextInt((max-min) + 1);
        Integer maxRand = tempMaxRand == 0 ? 1 : tempMaxRand;
        Integer minRand = tempMinRand == 0 ? 1 : tempMinRand;
        return (maxRand % minRand) + min;
    }


    /**
     * 随机获取月份
     * @return 随机月份
     */
    public static Integer monthGenerate() {
        return (new Random().nextInt(12)) + 1;
    }

    /**
     * 根据年份，月份生成天数
     * @return 当月天数
     */
    public static Integer dayByYearOrMonthGenerate(Integer year, Integer month) {

        Integer day = 0;
        // 定义闰年二月最多29天
        int leap2MonthMax = 29;
        // 定义平年二月最多28天
        int ping2MonthMax = 28;

        // 定义闰年其他月份最多31天
        int leapMonthMax = 31;
        // 定义平年其他月份最多28天
        int pingMonthMax = 30;

        Random random = new Random();

        // 如果是闰年
        if (isLeapYear(year)) {
            if (month == 2) {
                day = random.nextInt(leap2MonthMax);
            } else {
                day = random.nextInt(leapMonthMax);
            }
        } else {
            if (month == 2) {
                day = random.nextInt(ping2MonthMax);
            } else {
                day = random.nextInt(pingMonthMax);
            }
        }

        return day;
    }


    /**
     * 随机生成生日
     * @return 生日
     */
    public static String birthdayGenerate(String...fg) {
        String defaultFg = "";
        if (fg != null && fg.length > 0 && fg[0] != null && !"".equals(fg[0])) {
            defaultFg = fg[0];
        }
        Integer year = yearGenerate(2016, 1970);
        Integer month = monthGenerate();
        Integer day = dayByYearOrMonthGenerate(year, month);
        String yearStr = year.toString();
        String monthStr = month < 10 ? ("0" + month) : month.toString();
        String dayStr = day < 10 ? ("0" + day.toString()) : day.toString();

        return yearStr + defaultFg + monthStr + defaultFg + dayStr;
    }


    /**
     * 随机身份证号
     * @return 身份证号
     */
    public static String idCardGenerate() {
        Random random = new Random();
        Integer beforeNum = random.nextInt(899999) + 100000;
        Integer afterNum = random.nextInt(8999) + 1000;
        String before = beforeNum.toString();
        String birth = birthdayGenerate();
        String after = afterNum.toString();
        return (before + birth+ after);
    }

    /**
     * 随机身份证号
     * @return 身份证号
     */
    public static String idCardGenerate(String birthday) {
        Random random = new Random();
        Integer beforeNum = random.nextInt(899999) + 100000;
        Integer afterNum = random.nextInt(8999) + 1000;
        String before = beforeNum.toString();
        String after = afterNum.toString();
        return (before + birthday+ after);
    }



    /**
     * 判断该年份是否是闰年
     * @param year 要判断的年份
     * @return 是否闰年，是返回true 否返回false
     */
    public static boolean isLeapYear(Integer year) {

        boolean isLeapYear = false;

        if (year != null) {
            if (year % 100 == 0) {
                isLeapYear = (year % 400) == 0;
            } else {
                isLeapYear = (year % 4) == 0;
            }
        }
        return isLeapYear;
    }

}
