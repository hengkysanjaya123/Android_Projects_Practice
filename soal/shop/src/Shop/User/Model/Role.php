<?php
/**
 * Created by IntelliJ IDEA.
 * User: komalasurya
 * Date: 17/12/18
 * Time: 23.54
 */

namespace Shop\User\Model;


use Pandawa\Component\Ddd\AbstractModel;

class Role extends AbstractModel
{
    protected $fillable = [
        'name',
    ];
}