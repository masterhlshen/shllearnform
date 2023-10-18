package com.shl.ntimetable.service;

import java.util.LinkedList;
import java.util.List;

public class PkDemo {


    public static void main(String[] args) {
        List<CourseGrid> track = new LinkedList<>();
        List<CourseGrid> cg = new LinkedList<>();
        for (int col = 1; col <= 7; col++) {
            for (int row = 0; row <= 11; row++) {
                cg.add(new CourseGrid("", row, col));
            }
        }

        backtrack(cg, track, 0);
        System.out.println(res.size());
    }

    static List<List<CourseGrid>> res = new LinkedList<>();

    // 回溯方法
    static void backtrack(List<CourseGrid> cg, List<CourseGrid> track, int n) {

        // base case
        if (n == cg.size()) {
            // System.out.println("n=" + n);
            res.add(track);
            return;
        }

        for (int i = n; i < cg.size(); i++) {
            // 做选择
            track.add(cg.get(i));
            backtrack(cg, track,i + 1);

            // 撤销选择
            track.remove(track.size() - 1);
        }

    }

    // 课程格子
    static class CourseGrid {
        String classId;

        int x, y;

        public CourseGrid() {
        }

        public CourseGrid(String classId, int x, int y) {
            this.classId = classId;
            this.x = x;
            this.y = y;
        }
    }

    // 课程
    static class Course {

        public Course() {
        }

        public Course(String id, String cName, String teacherId, String teacherName) {
            this.id = id;
            this.cName = cName;
            this.teacherId = teacherId;
            this.teacherName = teacherName;
        }

        String id;
        String cName;
        String teacherId;
        String teacherName;
    }

    // 班级
    static class EduClass {

        public EduClass() {
        }

        public EduClass(String id, String name) {
            this.id = id;
            this.name = name;
        }

        String id;

        String name;
    }
}
