package io.wellstone.lecturejpawhiteship.common;

import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.Repository;
import org.springframework.lang.NonNull;
import org.springframework.lang.Nullable;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

@NoRepositoryBean
public interface MyRepository<T, ID extends Serializable> extends Repository<T, ID> {

    <E extends T> E save(@NonNull E entity);

    List<T> findAll();

    int count();

    @Nullable
    <E extends T> Optional<E> findById(ID id);
}
