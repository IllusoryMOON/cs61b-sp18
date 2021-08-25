public class Planet {

    double xxPos;
    double yyPos;
    double xxVel;
    double yyVel;
    double mass;
    String imgFileName;

    /* constructor */
    public Planet(double xP, double yP, double xV, double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    /* copy constructor */
    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    /* calculate the square of distance between two planets */
    public double calcDistance(Planet p) {
        return Math.sqrt((xxPos-p.xxPos)*(xxPos-p.xxPos) + (yyPos-p.yyPos)*(yyPos-p.yyPos));
    }

    /* calculate the force exerted by given planet */
    public double calcForceExertedBy(Planet p) {
        return 6.67e-11*mass*p.mass/(calcDistance(p)*calcDistance(p));
    }

    /* calculate the force exerted by given planet in the X direction. */
    public double calcForceExertedByX(Planet p) {
        return calcForceExertedBy(p)*(p.xxPos-xxPos)/calcDistance(p);
    }

    /* calculate the force exerted by given planet in the X direction. */
    public double calcForceExertedByY(Planet p) {
        return calcForceExertedBy(p)*(p.yyPos-yyPos)/calcDistance(p);
    }

    /* calculate the force exerted by given planet in the X direction. */
    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double NetForceX = 0;
        for (Planet p : allPlanets) {
            if (!p.equals(this)) {
                NetForceX += calcForceExertedByX(p);
            }
        }
        return NetForceX;
    }

    /* calculate the force exerted by given planet in the Y direction. */
    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double NetForceY = 0;
        for (Planet p : allPlanets) {
            if (!p.equals(this)) {
                NetForceY += calcForceExertedByY(p);
            }
        }
        return NetForceY;
    }

    public void update(double dt, double fX, double fY) {
        double aX = fX/mass;
        double aY = fY/mass;
        xxVel += dt*aX;
        yyVel += dt*aY;
        xxPos += dt*xxVel;
        yyPos += dt*yyVel;
    }

    /* draw the Planet’s image at the Planet’s position */
    public void draw() {
        StdDraw.picture(xxPos, yyPos, "images/" + imgFileName);
    }
}
