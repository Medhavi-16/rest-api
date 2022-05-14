package red.softn.npedidos.service;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.function.Supplier;

public abstract class CrudService<T, ID> {
    
    protected abstract CrudRepository<T, ID> getRepository();
    
    public List<T> findAll() {
        return (List<T>) getRepository().findAll();
    }
    
    public T findById(ID id) {
        return getRepository().findById(id)
                              .orElse(null);
    }
    
    public T save(T typeDish) {
        return getRepository().save(typeDish);
    }
    
    public void delete(ID id) {
        getRepository().deleteById(id);
    }
    
    public T update(ID id, T typeDish) {
        String message = "El registro no existe";
        Supplier<RuntimeException> dishNotFound = () -> new RuntimeException(message);
        
        this.getRepository()
            .findById(id)
            .orElseThrow(dishNotFound);
        //        typeDish.setId(id);
        
        return this.getRepository()
                   .save(typeDish);
    }
}