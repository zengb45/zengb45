/**
 * Created by SiyuanZeng's on 8/24/2016.
 */
public class Dependency {
    private String dependency;
    private boolean isInstalled;

    public String getDependency() {
        return dependency;
    }

    public void setDependency(String dependency) {
        this.dependency = dependency;
    }

    public boolean isInstalled() {
        return isInstalled;
    }

    public void setIsInstalled(boolean isInstalled) {
        this.isInstalled = isInstalled;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Dependency)) return false;

        Dependency that = (Dependency) o;

        return getDependency().equals(that.getDependency());

    }

    @Override
    public int hashCode() {
        return getDependency().hashCode();
    }
}
