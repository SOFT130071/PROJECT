package dao_impl;

import entity.HomeworkSubmit;

import java.util.List;
import java.util.Map;

public interface HomeworkSubmitDAO {
    int append(HomeworkSubmit homeworkSubmit);

    int delete(String uid, String hid);

    int modify(HomeworkSubmit homeworkSubmit);

    List<Map<String, String>> infoList(HomeworkSubmit homeworkSubmit);
}
