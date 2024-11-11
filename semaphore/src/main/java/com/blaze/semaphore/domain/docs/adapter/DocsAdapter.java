package com.blaze.semaphore.domain.docs.adapter;

import com.blaze.semaphore.domain.docs.entity.Docs;
import lombok.Builder;

public class DocsAdapter {
    private Docs docs;

    @Builder
    public DocsAdapter(Docs docs) {
        this.docs = docs;
    }

    public Docs getDocs() {
        return this.docs;
    }

    public String getTitle() {
        return this.docs.getTitle();
    }

    public Long getViewCount() {
        return this.docs.getViewCount();
    }

    public void viewCountIncrease() {
        this.docs.viewCountIncrease();
    }
}
