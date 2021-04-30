package com.boco.whl.funddemo.asm;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

public class CustomClassVisitor extends ClassVisitor {
    private String className;
    private String superName;

    public CustomClassVisitor(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }

    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.className = name;
        this.superName = superName;
    }

    @Override
    public MethodVisitor visitMethod(int access, String name, String descriptor, String signature, String[] exceptions) {
        System.out.println("ClassVisitor visitMethod name--->" + name + ", superName" + superName);
        MethodVisitor mv = cv.visitMethod(access, name, descriptor, signature, exceptions);
        // 判断是不是继承于AppCompatActivity，这个地方是androidx的，看自己项目的需求，但是涉及到第三方的jar包，一般都是连个都判断，找到Activity类几方法
        if (superName.equals("androidx/appcompat/app/AppCompatActivity")) {
            if (name.startsWith("onCreate")) {
                return new CustomMethodVisitor(mv, className, name);
            }
        }
        return mv;
    }

    @Override
    public void visitEnd() {
        super.visitEnd();
    }
}
