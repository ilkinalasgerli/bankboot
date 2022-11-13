package az.orient.bankboot.enums;

import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public enum EnumAvailableStatus {
    ACTIVE(1), DEACTIVE(0);
    private int value;

    public int getValue(){
        return value;
    }
}
