package ir.mapsa.wallet.base;

import ir.mapsa.wallet.exceptions.BaseException;
import ir.mapsa.wallet.exceptions.IdNullException;
import ir.mapsa.wallet.exceptions.NotFoundExceptions;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;


@Transactional
public class BaseServiceImpl<T extends BaseEntity,ID extends Serializable,
        R extends JpaRepository<T,ID>> implements BaseService<T,ID> {

    protected final R repository;

    public BaseServiceImpl(R repository) {
        this.repository = repository;
    }

    @Override
    public T saveOrUpdate(T t) throws BaseException {
        return repository.save(t);
    }

    @Override
    public void deleteById(ID id) throws BaseException {
        repository.delete(findById(id));
    }


    @Override
    public void deleteEntity(T t) throws BaseException {
        repository.delete(t);
    }

    @Override
    public T findById(ID id) throws BaseException {
        if (id==null){
            throw new IdNullException("id is null");
        }else {
            return repository.findById(id).orElseThrow(() -> {
                throw new NotFoundExceptions("your entity not found");
            });
        }
    }

    @Override
    public List<T> findAll() throws BaseException {
        return repository.findAll();
    }

//    @Override
//    public <S extends T> List<S> findAllByExample(Example<S> example) {
//        return null;
//    }
}
