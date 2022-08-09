package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

public final class Site extends Contato {
    private String url;

    public Site(String url) {
        this.url = url;
    }

    public Site(Integer id, String url) {
        this.url = url;
        this.id = id;
    }

    public void update(final String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return this.url;
    }
}
