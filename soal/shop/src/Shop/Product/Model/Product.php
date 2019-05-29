<?php
/**
 * Created by IntelliJ IDEA.
 * User: komalasurya
 * Date: 17/12/18
 * Time: 23.46
 */

namespace Shop\Product\Model;


use Pandawa\Component\Ddd\AbstractModel;

class Product extends AbstractModel
{
    protected $fillable = [
        'name',
        'price',
        'stock'
    ];
}