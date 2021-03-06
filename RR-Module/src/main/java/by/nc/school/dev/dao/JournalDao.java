package by.nc.school.dev.dao;

import by.nc.school.dev.dao.entities.JournalDaoEntity;
import by.nc.school.dev.data.TestJournalDb;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class JournalDao extends BaseDao<JournalDaoEntity> {

    private TestJournalDb testJournalDb;

    public JournalDao() {
        testJournalDb = new TestJournalDb();
        entities = testJournalDb.deserialize();
    }

    public List<JournalDaoEntity> getJournalForGroup(int groupNumber) {
//        List<JournalDaoEntity> result = new ArrayList<>();
        UserDao userDao = new DaoFactory().getUserDao();
//        for (JournalDaoEntity entity : entities) {
//            if (userDao.get(entity.getStudentId()).getGroupNumber() == groupNumber) {
//                result.add(entity);
//            }
//        }
        return entities.stream().filter(entity -> userDao.get(entity.getStudentId()).getGroupNumber() == groupNumber)
                .collect(Collectors.toList());
    }

    @Override
    public JournalDaoEntity create(JournalDaoEntity entity) {
        entities.add(entity);
        testJournalDb.serialize(entities);
        return entity;
    }

    @Override
    public void update(JournalDaoEntity entity) {

    }

    @Override
    public void delete(JournalDaoEntity entity) {

    }
}
