package org.andres_k.web.app.utils.data.cache;

import org.andres_k.web.app.utils.tools.Console;

import java.util.List;

public abstract class ACache<T> {
    protected List<T> items;

    public List<T> get() {
        return this.items;
    }

    public void save(T item) {
        int index = find(item);
        if (index == -1) {
            this.items.add(item);
        } else {
            this.items.set(index, item);
        }
        this.persist();
    }

    public void delete(T item) {
        int index = find(item);
        if (index == -1) {
            Console.log_err("Cache: item [" + item.toString() + "] can't be removed: doesnt exist");
        }
        this.items.remove(index);
        this.persist();
    }

    protected abstract int find(T item);

    protected abstract void persist();
}
