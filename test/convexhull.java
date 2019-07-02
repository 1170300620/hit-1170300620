package test;

import java.util.HashSet;
import java.util.Set;

public class convexhull {

  /**
   * Given a set of points, compute the convex hull, the smallest convex set that contains all the points 
   * in a set of input points. We adopt the exhaustive method to solve this problem.
   * 
   * @param points a set of points with xCoords and yCoords. It might be empty, contain only 1 point, two points or more.
   * @return minimal subset of the input points that form the vertices of the perimeter of the convex hull
   */
  public static Set<Point> convexHull(Set<Point> points) {
      int size = points.size();
      Point [] pointlist = new Point [size];
      pointlist = points.toArray(new Point[size]);
      Set <Point> extra_points = new HashSet <Point>();
      if(size > 0) {//检查点数是否为0
          Point firstpoint = pointlist[0];
          for(int i = 0; i < size; i++)
          {
              if(pointlist[i].x() < firstpoint.x())
              {
                  firstpoint = pointlist[i];
              }
              else
              {
                  if(pointlist[i].x() == firstpoint.x() && pointlist[i].y() < firstpoint.y())
                      firstpoint = pointlist[i];
              }
              //System.out.println("(" + pointlist[i].x() + "," + pointlist[i].y() + ")");
          }//找出左下角的凸包顶点
      
      
          extra_points.add(firstpoint);//将第一个凸包顶点加入凸包顶点集合
         // System.out.println("p:" + "(" + firstpoint.x() + "," + firstpoint.y() + ")");
          for(int i = 0; i < size; i++)
          {
              if(!extra_points.contains(pointlist[i])) {
                  double k = (pointlist[i].y() - firstpoint.y()) * (pointlist[i].x() - firstpoint.x());
                  double b = firstpoint.y() * (pointlist[i].x() - firstpoint.x()) * (pointlist[i].x() - firstpoint.x());
                  Point addpoint = pointlist[i];
                  int allabove = 0;
                  int allbelow = 0;
                  for(int j = 0; j < size; j++)//递归地、逆时针地找出凸包顶点
                  {
                      if(j != i && pointlist[j] != firstpoint)
                      {
                          double f = k * (pointlist[j].x() - firstpoint.x()) + b;
                          if(pointlist[i].x() - firstpoint.x() != 0) {//检查直线是否符合条件
                              if(f > pointlist[j].y() * (pointlist[i].x() - firstpoint.x()) * (pointlist[i].x() - firstpoint.x()))
                                  allbelow = 1;
                              else {
                                  if(f < pointlist[j].y() * (pointlist[i].x() - firstpoint.x()) * (pointlist[i].x() - firstpoint.x()))
                                      allabove = 1;
                                  else {
                                      if((Math.abs(pointlist[j].x() - firstpoint.x()) > Math.abs(pointlist[i].x() - firstpoint.x()))
                                          || (Math.abs(pointlist[j].y() - firstpoint.y()) > Math.abs(pointlist[i].y() - firstpoint.y())))
                                          addpoint = pointlist[j];
                                  }
                              }
                          }
                          else
                          {
                              if(pointlist[j].x() > pointlist[i].x())
                                  allabove = 1;
                              else {
                                  if(pointlist[j].x() < pointlist[i].x())
                                      allbelow = 1;
                                  else 
                                      if((Math.abs(pointlist[j].y() - firstpoint.y()) > Math.abs(pointlist[i].y() - firstpoint.y())))
                                          addpoint = pointlist[j];
                              }
                          }
                      }
                      if(allbelow == 1 && allabove == 1)
                          break;
                  }
                  if(!(allbelow == 1 && allabove == 1))
                  {
                      extra_points.add(addpoint);
                      firstpoint = addpoint;
                      i = 0;
                      //System.out.println("p:" + "(" + addpoint.x() + "," + addpoint.y() + ")");
                  }
              }
          }
      }
      //System.out.println();
      return extra_points;
  }
}
