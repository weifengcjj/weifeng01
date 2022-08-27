import com.alibaba.fastjson.JSON;

/**
 * @Author 微风
 * @Version 1.0.0
 * @StartTime Start
 * @EndTime End
 */
public class Json {
    public static String jsonString(Model model)          //返回字符串JSON的字符串
    {
        return JSON.toJSONString(model);
    }
}
