package Robot;


/**
 * ���������ÿ���Ľ��
 * @author PfC
 *
 */
public class Step
{
    int boxRowCount = 0;
    int boxColCount = 0;
    /**
     * ��ʱ�ķ�����δ���
     */
    int chage = 0;
    /**
     * ��ʱ�����X����
     */
    int x = 0;
    /**
     * �ո�ĸ���
     */
    int spaceCount = 0;// 1
    /**
     * �ո��γɵ������������
     */
    int rectCount = 0;//
    /**
     * ����������еĸ߶�
     */
    int height = 0;// 2
    /**
     * ÿ���пո��γɵ���ֵ
     */
    int spaceNumber = 0;//
    /**
     * ��һ�����õ���Ȩֵ
     */
    int roleValue = 0;
    /**
     * ��һ����ȥ�ķ�������
     */
    int line = 0;// 0

    /**
     * ����������һ������
     */
    public Step(int rows, int cols)
    {
        this.boxColCount = cols;
        this.boxRowCount = rows;
        chage = 0;
        x = 0;
        spaceCount = Integer.MAX_VALUE;
        rectCount = Integer.MAX_VALUE;
        height = Integer.MAX_VALUE;
        spaceNumber = Integer.MAX_VALUE;
        roleValue = Integer.MIN_VALUE;
    }

    public int getSpaceCount()
    {
        return spaceCount;
    }

    public int getSpaceNumber()
    {
        return spaceNumber;
    }

    public int getHeight()
    {
        return height;
    }

    public int getLine()
    {
        return line;
    }

    public int getRectCount()
    {
        return rectCount;
    }

    public int getRoleValue()
    {
        return roleValue;
    }

    public void setSpaceCount(int spaceCount)
    {
        this.spaceCount = spaceCount;
    }

    public void setRectCount(int rectCount)
    {
        this.rectCount = rectCount;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public void setSpaceNumber(int spaceNumber)
    {
        this.spaceNumber = spaceNumber;
    }

    public void setRoleValue(int roleValue)
    {
        this.roleValue = roleValue;
    }

    public void setLine(int line)
    {
        this.line = line;
    }

    public int getChage()
    {
        return chage;
    }

    public void setChage(int chage)
    {
        this.chage = chage;
    }

    public int getX()
    {
        return x;
    }

    public void setX(int x)
    {
        this.x = x;
    }

    /**
     * �Ƚ��������������
     *
     * @return ������Ż����� 1 ��ͬ���� 0 ��󷵻� -1
     */
    public int compareTo(Step step)
    {
        if (height < boxRowCount / 3)
        {
            // �ڶ��ַ���
            if (spaceCount < step.spaceCount)
                return 1;
            if (spaceCount == step.spaceCount)
            {
                if (line > step.line)
                    return 1;
                if (line == step.line)
                {
                    if (height < step.height)
                        return 1;
                    if (height == step.height)
                    {
                        if (rectCount < step.rectCount)
                            return 1;
                        if (rectCount == step.rectCount)
                        {
                            if (spaceNumber < step.spaceNumber)
                                return 1;
                            if (spaceNumber == step.spaceNumber)
                                return 0;
                            return -1;
                        }
                        return -1;
                    }
                    return -1;
                }
                return -1;
            }
            return -1;
        }
        else
        {
            // ��һ�ַ���
            if (line > step.line)
                return 1;
            if (line == step.line)
            {
                if (spaceCount < step.spaceCount)
                    return 1;
                if (spaceCount == step.spaceCount)
                {
                    if (height < step.height)
                        return 1;
                    if (height == step.height)
                    {
                        if (rectCount < step.rectCount)
                            return 1;
                        if (rectCount == step.rectCount)
                        {
                            if (spaceNumber < step.spaceNumber)
                                return 1;
                            if (spaceNumber == step.spaceNumber)
                                return 0;
                            return -1;
                        }
                        return -1;
                    }
                    return -1;
                }
                return -1;
            }
            return -1;
        }
    }
}
