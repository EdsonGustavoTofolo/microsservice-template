package io.github.edsongustavotofolo.microservicetemplate.domain.entities;

public final class Site extends Contato {
    private String url;

    public Site(final String url) {
        this.url = url;
    }

    public Site(final Integer id, final String url) {
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

    @Override
    public boolean isSame(final Contato other) {
        if (other instanceof Site otherSite) {
            return this.url.equals(otherSite.url);
        }
        return false;
    }

    @Override
    public void update(final Contato other) {
        if (other instanceof Site otherSite) {
            otherSite.url = this.url;
        }
    }
}
