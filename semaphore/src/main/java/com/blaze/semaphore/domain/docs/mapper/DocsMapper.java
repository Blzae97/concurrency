package com.blaze.semaphore.domain.docs.mapper;

import com.blaze.semaphore.domain.docs.adapter.DocsAdapter;
import com.blaze.semaphore.domain.docs.entity.Docs;

public class DocsMapper implements Mapper<Docs, DocsAdapter> {
    @Override
    public DocsAdapter toAdapter(Docs entity) {
        return new DocsAdapter(entity);
    }
}
