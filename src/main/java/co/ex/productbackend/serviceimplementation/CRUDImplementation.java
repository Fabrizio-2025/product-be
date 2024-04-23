package co.ex.productbackend.serviceimplementation;

import co.ex.productbackend.repositories.GenericRepo;
import co.ex.productbackend.services.ICRUD;

import java.util.List;

public abstract class CRUDImplementation <T,ID> implements ICRUD<T,ID> {
    protected abstract GenericRepo<T,ID> getRepo();

    @Override
    public T registrar(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public T modificar(T t) throws Exception {
        return getRepo().save(t);
    }

    @Override
    public List<T> listar() throws Exception {
        return getRepo().findAll();
    }

    @Override
    public T listarPorId(ID id) throws Exception {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public void eliminar(ID id) throws Exception {
        getRepo().deleteById(id);
    }
}
