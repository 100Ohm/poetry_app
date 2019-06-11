package xyz.a100ohm.poems.mvp.model;

/**
 * <p>项目名称: poetry_app </p>
 * <p>文件名称: null.java </p>
 * <p>创建时间: 2019/6/11 15:22</p>
 * <p>企业信息: 广东工业大学 数字媒体技术专业</p>
 *
 * @author <a href="mail to: 100ohmYeah@gmail.com" rel="nofollow">一百欧姆</a>
 * @version v1.0
 * @update [1][2019/6/11] [一百欧姆][网络请求方面的model,提供api,帮忙请求一些Bomb数据等]
 */
public class NetworkModel {
    private static NetworkModel model;

    public static NetworkModel getInstance() {
        if(model == null)
            synchronized (NetworkModel.class) {
                if (model == null)
                    model = new NetworkModel();
            }
        return model;
    }
}
