package by.nc.school.dev.builders;

import by.nc.school.dev.dao.DaoFactory;
import by.nc.school.dev.dao.GroupDao;
import by.nc.school.dev.enitities.Curator;
import by.nc.school.dev.enitities.Group;
import by.nc.school.dev.dao.entities.GroupDaoEntity;
import by.nc.school.dev.dao.UserDao;
import by.nc.school.dev.enitities.Student;
import by.nc.school.dev.enitities.Tutor;
import by.nc.school.dev.services.UserService;

import java.util.ArrayList;
import java.util.List;

public class GroupBuilder {

    public Group build(int groupNumber) {
        List<GroupDaoEntity> entities = new GroupDao().getGroupByGroupNumber(groupNumber);
        return build(entities);
    }

    public Group build(List<GroupDaoEntity> groupDaoEntities) {
        UserDao userDao = new DaoFactory().getUserDao();
        List<Student> students = new ArrayList<>();
        StudentBuilder studentBuilder = new StudentBuilder();
        Curator curator = null;
        int groupNumber = 0;
        UserService userService = new UserService();
        for (GroupDaoEntity groupDaoEntity : groupDaoEntities) {
            if (curator == null) {
                curator = (Curator)userService.getUser(userDao.get(groupDaoEntity.getCuratorId()));
                groupNumber = groupDaoEntity.getGroupNumber();
            }
            students.add((Student)studentBuilder.build(userDao.get(groupDaoEntity.getStudentsId())));
        }
        if (curator == null) {
            throw new RuntimeException("unable to build group. Curator doesn't exist");
        }
        return new Group(groupNumber, students, curator);
    }
}
